package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSWaiter {

    private static WebDriver jsWaitDriver;
    private static WebDriverWait jsWait;
    private static JavascriptExecutor jsExec;

    // Get the driver
    public static void setDriver(WebDriver driver) {
        jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 30);
        jsExec = (JavascriptExecutor) jsWaitDriver;
    }

    public void waitGifLoading(By resp) {
        try {
            if (jsWaitDriver.findElement(resp).isEnabled()) {
//				 System.out.println("TEM LOADING");
//				 jsWaitDriver.findElement(resp).getCssValue("display") + "| " + resp.toString());
                if (("block".equals(jsWaitDriver.findElement(resp).getCssValue("display")))) {
//					 System.out.println("GIF block is NOT Ready!");
                    jsWait.until(ExpectedConditions.invisibilityOfElementLocated(resp));
//					 System.out.println("PAROU DE RODAR");
                }
                if (!("none".equals(jsWaitDriver.findElement(resp).getCssValue("display")))) {
//					 System.out.println("GIF none is NOT Ready!");
                    jsWait.until(ExpectedConditions.invisibilityOfElementLocated(resp));
                    // System.out.println("PAROU DE RODAR");
                } else {
                    // System.out.println("GIF is Ready!");
                }
            }
        } catch (Exception e) {
            // System.out.println("GIF is not defined on this site!");
        }

    }


    private void ajaxComplete() {
        jsExec.executeScript("var callback = arguments[arguments.length - 1];" + "var xhr = new XMLHttpRequest();"
                + "xhr.open('GET', '/Ajax_call', true);" + "xhr.onreadystatechange = function() {"
                + "  if (xhr.readyState == 4) {" + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    }

    private void waitForJQueryLoad() {
        try {
            ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((Long) ((JavascriptExecutor) jsWaitDriver)
                            .executeScript("return jQuery.active") == 0);
                }
            };

            boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

            if (!jqueryReady) {
                jsWait.until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitForAngularLoad() {
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        angularLoads(angularReadyScript);
    }
//	waitUntilJSReady

    public boolean waitForLoadPage() {
        boolean resp = false;
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(jsWaitDriver, 30);
            resp = wait.until(pageLoadCondition);
            return resp;
        } catch (Exception e) {
            return resp;
        }

    }

    public void waitUntilJSReady() {
        try {
            ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) jsWaitDriver)
                            .executeScript("return document.readyState").toString().equals("complete");
                }
            };

            boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

            if (!jsReady) {
                jsWait.until(jsLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitUntilJQueryReady() {
        Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined) {
            poll(20);

            waitForJQueryLoad();

            poll(20);
        }
    }

    public void waitUntilAngularReady() {
        try {
            Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
            if (!angularUnDefined) {
                Boolean angularInjectorUnDefined = (Boolean) jsExec
                        .executeScript("return angular.element(document).injector() === undefined");
                if (!angularInjectorUnDefined) {
                    poll(20);

                    waitForAngularLoad();

                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitUntilAngular5Ready() {
        try {
            Object angular5Check = jsExec
                    .executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
            if (angular5Check != null) {
                Boolean angularPageLoaded = (Boolean) jsExec
                        .executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
                if (!angularPageLoaded) {
                    poll(20);

                    waitForAngular5Load();

                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitForAngular5Load() {
        String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
        angularLoads(angularReadyScript);
    }

    private void angularLoads(final String angularReadyScript) {
        try {
            ExpectedCondition<Boolean> angularLoad = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return Boolean
                            .valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());
                }
            };

            boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

            if (!angularReady) {
                jsWait.until(angularLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitAllRequest() {
        waitUntilJSReady();
        waitForLoadPage();
        ajaxComplete();
        waitUntilJQueryReady();
        waitUntilAngularReady();
        waitUntilAngular5Ready();
    }

    /**
     * Method to make sure a specific element has loaded on the page
     *
     * @param by
     * @param expected
     */
    public void waitForElementAreComplete(final By by, final int expected) {
        ExpectedCondition<Boolean> angularLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                int loadingElements = jsWaitDriver.findElements(by).size();
                return loadingElements >= expected;
            }
        };
        jsWait.until(angularLoad);
    }

    /**
     * Waits for the elements animation to be completed
     *
     * @param css
     */
    public void waitForAnimationToComplete(final String css) {
        ExpectedCondition<Boolean> angularLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                int loadingElements = DriverFactory.getDriver().findElements(By.cssSelector(css)).size();
                return loadingElements == 0;
            }
        };
        jsWait.until(angularLoad);
    }

    public void poll(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package browserinstance;

import com.microsoft.playwright.*;


public class BrowserInstance {
    public Playwright playwright;
    public Browser browser;
    public BrowserContext browserContext;
    public Page page;


    private final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();


    public Page launchBrowserInstance(String browserName){

        playwrightThreadLocal.set(Playwright.create());
        playwright= playwrightThreadLocal.get();
        BrowserType.LaunchOptions lop =new BrowserType.LaunchOptions();
        lop.setHeadless(false);
        switch (browserName.toLowerCase()){
            case "chrome":
                lop.setChannel("chrome");
                browser = playwright.chromium().launch(lop);
                break;
            case "firefox":
                lop.setChannel("firefox");
                browser = playwright.firefox().launch(lop);
                break;
            case "webkit":
                lop.setChannel("webkit");
                browser = playwright.webkit().launch(lop);
            case "edge":
                lop.setChannel("msedge");
                browser = playwright.chromium().launch(lop);
            default :
                lop.setChannel("webkit");
                browser = playwright.chromium().launch(lop);
                break;
        }

        browserThreadLocal.set(browser);
        browser = browserThreadLocal.get();
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setIgnoreHTTPSErrors(true));



        // Enable tracing for UI tests
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));
        contextThreadLocal.set(browserContext);
        browserContext = contextThreadLocal.get();
        page = browserContext.newPage();
        //page.setDefaultTimeout(30000);
        pageThreadLocal.set(page);
        page = pageThreadLocal.get();
        return page;

//       playwright= Playwright.create();
//       browser = playwright.chromium().launch();
//       page = browser.newPage();
//       return page;
    }

    public Playwright getPlaywright(){
        return  playwright;
    }
    public Browser getBrowserObj(){
        return  browser;
    }
    public BrowserContext getBrowserContext(){
        return  browserContext;
    }
    public Page getPage(){return page;}



}



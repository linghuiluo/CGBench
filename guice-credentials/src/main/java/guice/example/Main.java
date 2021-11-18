package guice.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.example.classes.App;
import guice.example.modules.AppCredentialsModule;
import guice.example.modules.AppModule;
import guice.example.modules.TestCredentialsModule;

public class Main {

    public static void main(final String[] argv) {
        // A static analysis should detect that AppModule receives hardcoded credentials from TestCredentialsModule.
        final Injector injector = Guice.createInjector(new AppModule(), new TestCredentialsModule());
        final App badApp = injector.getInstance(App.class);
        badApp.run();

        // This app is technically ok, but it would be handy if the analysis still understands that there is another
        // module that could provide hard-coded credentials. Basically, we're asking if there is any configuration
        // that is bad, not just the configuration that is used at runtime.
        final Injector injector2 = Guice.createInjector(new AppModule(), new AppCredentialsModule());
        final App goodApp = injector2.getInstance(App.class);
        goodApp.run();
    }

}

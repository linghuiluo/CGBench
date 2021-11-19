package guice.example.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import guice.example.classes.App;
import static guice.example.modules.Constants.PASSWORD_KEY;
import static guice.example.modules.Constants.USER_NAME_KEY;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    public App providesApp(@Named(USER_NAME_KEY) String userName, @Named(PASSWORD_KEY) String password) {
        return new App(userName, password);
    }
}

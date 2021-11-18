package guice.example.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import static guice.example.modules.Constants.PASSWORD_KEY;
import static guice.example.modules.Constants.USER_NAME_KEY;

public class AppCredentialsModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Named(USER_NAME_KEY)
    public String provideUserName() {
        return System.getenv().get("USER");
    }

    @Provides
    @Named(PASSWORD_KEY)
    public String providePassword() {
        return System.getenv().get("HOME");
    }

}

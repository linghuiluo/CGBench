package guice.example.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import static guice.example.modules.Constants.PASSWORD_KEY;
import static guice.example.modules.Constants.USER_NAME_KEY;

public class TestCredentialsModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Named(USER_NAME_KEY)
    public String provideUserName() {
        return "hardcoded user";
    }

    @Provides
    @Named(PASSWORD_KEY)
    public String providePassword() {
        return "hardcoded password";
    }

}

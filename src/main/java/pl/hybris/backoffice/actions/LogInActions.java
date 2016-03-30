package pl.hybris.backoffice.actions;

import pl.hybris.backoffice.pageobjects.LogInBlock;

/**
 * Created by i323728 on 15.03.2016.
 */
public class LogInActions
{

    private LogInBlock logInBlock;

    public LogInActions()
    {
        logInBlock = new LogInBlock();

    }

    public LogInActions logIn(String user, String pass)
    {
        logInBlock.synchronize()
                .setLanguage("English")
                .setUser(user)
                .setPass(pass)
                .clickLogIn();
        return this;
    }





}

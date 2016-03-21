package pl.hybris.core.exceptions;

/**
 * Created by i323728 on 18.03.2016.
 */
public class SomethingWentWrongException extends Exception
{
    public SomethingWentWrongException() {}


    public SomethingWentWrongException(String message)
    {
        super(message);
    }
}

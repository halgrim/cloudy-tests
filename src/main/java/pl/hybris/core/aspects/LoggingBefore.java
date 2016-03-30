package pl.hybris.core.aspects;

import com.jamesmurty.utils.XMLBuilder2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.joda.time.DateTime;
import pl.hybris.core.reporting.UniqueXMLReportGenerator;


/**
 * Created by i323728 on 22.03.2016.
 */
@Aspect
public class LoggingBefore
{

	@Before("execution(* pl.hybris.backoffice..actions..*(..))")
	public void logActions(JoinPoint joinPoint)
	{

		DateTime dateTime = new DateTime();
		XMLBuilder2 xml = UniqueXMLReportGenerator.getCurrentReporter().getBuilder();
		String arguments = "";
		Object[] args = joinPoint.getArgs();

		for (int i = 0; i < args.length; i++)
		{
			arguments = arguments + args[i].toString() + ",";
		}

		if (args.length > 0)
		{
			arguments = arguments.substring(0, arguments.length() - 1);
		}

		XMLBuilder2 appendedReport = xml
				.e("action") //
				.a("class-name", joinPoint.getTarget().getClass().getName()) //
				.a("name", joinPoint.getSignature().getName()) //
				.a("start-time", dateTime.toString()) //
				.a("arguments", arguments);
		//if (UniqueXMLReportGenerator.getCurrentReporter().increaseLevel() == 1) //
		//{

		//}
		UniqueXMLReportGenerator.getCurrentReporter().setBuilder(appendedReport);

	}


	@Before("execution(* pl.hybris.backoffice..pageobjects..*(..))")
	public void logPageObject(JoinPoint joinPoint)
	{

		DateTime dateTime = new DateTime();
		XMLBuilder2 xml = UniqueXMLReportGenerator.getCurrentReporter().getBuilder();
		String arguments = "";
		Object[] args = joinPoint.getArgs();

		for (int i = 0; i < args.length; i++)
		{
			arguments = arguments + args[i].toString() + ",";
		}

		if (args.length > 0)
		{
			arguments = arguments.substring(0, arguments.length() - 1);
		}

		XMLBuilder2 appendedReport = xml
				.e("page-object") //
				.a("class-name", joinPoint.getTarget().getClass().getName()) //
				.a("name", joinPoint.getSignature().getName()) //
				.a("start-time", dateTime.toString()) //
				.a("arguments", arguments);
		//if (UniqueXMLReportGenerator.getCurrentReporter().increaseLevel() == 1) //
		//{

		//}
		UniqueXMLReportGenerator.getCurrentReporter().setBuilder(appendedReport);


	}



}

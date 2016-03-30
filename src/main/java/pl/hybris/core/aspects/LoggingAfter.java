package pl.hybris.core.aspects;

import com.jamesmurty.utils.XMLBuilder2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;
import pl.hybris.core.reporting.InitializeTestData;
import pl.hybris.core.threading.CurrentThreadDriver;
import pl.hybris.core.reporting.UniqueXMLReportGenerator;
import pl.hybris.util.CommonUtil;

/**
 * Created by i323728 on 11.03.2016.
 */
@Aspect
public class LoggingAfter
{

    // @Pointcut("execution(String cashrec.core.CommandBuilder.buildCommand(..))")
    // public void buildCommandMethod() {  }
    // @AroundcbuildCommandMethod() && if(false)")
    // public Object logTimeMethod(ProceedingJoinPoint joinPoint)


    //"execution(* cashrec.page.blocks..*( ..)) 11 execution(* cashrec.page.objecs. *.*(..)) 11 execution(* cashrec.actions.*.*(..))", returning = "f")
    @AfterReturning(
            pointcut = "execution(* pl.hybris.backoffice..actions..*(..))" ,
            returning = "object")
    public void logActions(JoinPoint jointPoint, Object object)
    {

        XMLBuilder2 xml = UniqueXMLReportGenerator.getCurrentReporter().getBuilder();
        DateTime dateTime = new DateTime();


        XMLBuilder2 appendedReport = xml
                .a("finish-time", dateTime.toString())
                .a("returns", String.valueOf(object))
                .up();


        UniqueXMLReportGenerator.getCurrentReporter().setBuilder(appendedReport);

        InitializeTestData.saveReportToDB();
    }


    @AfterReturning(
            pointcut = "execution(* pl.hybris.backoffice..pageobjects..*(..))",
            returning = "object")
    public void logPageObject(JoinPoint jointPoint, Object object)
    {

        XMLBuilder2 xml = UniqueXMLReportGenerator.getCurrentReporter().getBuilder();
        DateTime dateTime = new DateTime();


        XMLBuilder2 appendedReport = xml
                .a("finish-time", dateTime.toString())
                .a("returns", String.valueOf(object))
                .up();

        UniqueXMLReportGenerator.getCurrentReporter().setBuilder(appendedReport);

    }

    @AfterReturning("execution(* pl.hybris.backoffice.pageobjects..synchronize())")
    public void logSync(JoinPoint joinPoint)
    {
        DateTime dateTime = new DateTime();
        XMLBuilder2 xml = UniqueXMLReportGenerator.getCurrentReporter().getBuilder();
        String arguments = "";
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++)
        {
            arguments = args[i].toString() + ",";
        }

        if (args.length > 0)
        {
            arguments.substring(0, arguments.length() - 1);
        }

        XMLBuilder2 appendedReport = xml
                .e("page-object-synchronize") //
                .a("class-name", joinPoint.getTarget().getClass().getName()) //
                .a("name", joinPoint.getSignature().getName()) //
                .a("timestamp", dateTime.toString())
                .up(); //

        String fileName = CommonUtil.doAPrintScreen(CurrentThreadDriver.getCurrentDriver(), "After_synchronize_method");
        appendedReport.a("screenshot-synchronize", fileName);

        UniqueXMLReportGenerator.getCurrentReporter().setBuilder(appendedReport);

    }
}







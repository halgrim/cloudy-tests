package pl.hybris.core.reporting;

/**
 * Created by i323728 on 22.03.2016.
 */
public class UniqueXMLReportGenerator
{

        private static final TestReportXMLBuilder xmlBuilder = new TestReportXMLBuilder();

        private static final ThreadLocal <TestReportXMLBuilder> threadLocal =
                new ThreadLocal <TestReportXMLBuilder> () {
                    @Override protected TestReportXMLBuilder initialValue() {
                        return xmlBuilder.initialize();
                    }
                };

        public static TestReportXMLBuilder getCurrentReporter() {
            return threadLocal.get();
        }

}

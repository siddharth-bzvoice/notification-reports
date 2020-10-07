package com.bazaarvoice;

import com.bazaarvoice.db.AdaptorReportDao;
import com.bazaarvoice.db.DataRetentionStatisticsDao;
import com.bazaarvoice.db.ReportMasterDao;
import com.bazaarvoice.models.AdaptorReport;
import com.bazaarvoice.models.DataRetentionStatistics;
import com.bazaarvoice.models.ReportMaster;
import com.bazaarvoice.resources.AdaptorReportResource;
import com.bazaarvoice.resources.DataRetentionStatisticsResource;
import com.bazaarvoice.resources.ReportMasterResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class NotificationsReportsApplication extends Application<NotificationReportsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new NotificationsReportsApplication().run(args);
    }

    @Override
    public String getName() {
        return "Notification Reports";
    }

    private final SwaggerBundle<NotificationReportsConfiguration> swagger = new SwaggerBundle<NotificationReportsConfiguration>(){
        @Override
        public SwaggerBundleConfiguration getSwaggerBundleConfiguration(NotificationReportsConfiguration configuration) {
            return configuration.getSwaggerBundleConfiguration();
        }
    };

    private final HibernateBundle<NotificationReportsConfiguration> hibernate = new HibernateBundle<NotificationReportsConfiguration>(
            AdaptorReport.class,
            DataRetentionStatistics.class,
            ReportMaster.class
            ) {
        @Override
        public DataSourceFactory getDataSourceFactory(NotificationReportsConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<NotificationReportsConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(swagger);
    }

    @Override
    public void run(final NotificationReportsConfiguration configuration, final Environment environment) {

        final AdaptorReportDao adaptorReportDao = new AdaptorReportDao(hibernate.getSessionFactory());
        final DataRetentionStatisticsDao dataRetentionStatisticsDao = new DataRetentionStatisticsDao(hibernate.getSessionFactory());
        final ReportMasterDao reportMasterDao = new ReportMasterDao(hibernate.getSessionFactory());

        environment.jersey().register(new AdaptorReportResource(adaptorReportDao));
        environment.jersey().register(new DataRetentionStatisticsResource(dataRetentionStatisticsDao));
        environment.jersey().register(new ReportMasterResource(reportMasterDao));
    }

}

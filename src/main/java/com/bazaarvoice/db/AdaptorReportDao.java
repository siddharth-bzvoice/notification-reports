package com.bazaarvoice.db;

import com.bazaarvoice.models.AdaptorReport;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AdaptorReportDao extends AbstractDAO<AdaptorReport> {

    public AdaptorReportDao(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public AdaptorReport findById(String arId){
        return get(arId);
    }

    @SuppressWarnings("unchecked")
    public List<AdaptorReport> getAdaptorReports(){
        return list((Query<AdaptorReport>) namedQuery("com.bazaarvoice.models.AdapterReport.findAll"));
    }
}

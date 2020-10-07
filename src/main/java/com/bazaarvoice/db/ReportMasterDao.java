package com.bazaarvoice.db;

import com.bazaarvoice.models.ReportMaster;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ReportMasterDao extends AbstractDAO<ReportMaster> {

    public ReportMasterDao(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public ReportMaster findById(String arId){
        return get(arId);
    }

    @SuppressWarnings("unchecked")
    public List<ReportMaster> getReportMasters(){
        return list((Query<ReportMaster>) namedQuery("get_adapter_reports"));
    }

    public void create(ReportMaster reportMaster){
        currentSession().save(reportMaster);
    }
}

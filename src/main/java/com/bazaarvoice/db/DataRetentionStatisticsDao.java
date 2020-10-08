package com.bazaarvoice.db;

import com.bazaarvoice.models.DataRetentionStatistics;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DataRetentionStatisticsDao extends AbstractDAO<DataRetentionStatistics> {

    public DataRetentionStatisticsDao(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public DataRetentionStatistics findById(String id){
        return get(id);
    }

    @SuppressWarnings("unchecked")
    public List<DataRetentionStatistics> getDataRetentionStatistics(){
        return list((Query<DataRetentionStatistics>) namedQuery("get_data_retention_statistics"));
    }

    @SuppressWarnings("unchecked")
    public List<DataRetentionStatistics> getDataRetentionStatisticsByClientName(String clientname){
        return list((Query<DataRetentionStatistics>)
        namedQuery("get_data_retention_statistics_by_clientname").setParameter("clientname", clientname));
    }

    public void create(DataRetentionStatistics dataRetentionStatistics){
        currentSession().save(dataRetentionStatistics);
    }
}


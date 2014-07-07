package com.example.server;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(WidgetsResource.WidgetsMapper.class)
public interface Widgets {

    public static final class DatabaseContract {
        public static final String TABLE_NAME = "widgets";
        public static final String ID_COLUMN = "id";
        public static final String NAME_COLUMN = "name";
        public static final String DATE_CREATED_COLUMN = "date_created";
    }

    @SqlUpdate("create table " + Widgets.DatabaseContract.TABLE_NAME + " (" +
            Widgets.DatabaseContract.ID_COLUMN + " bigint auto_increment, " +
            Widgets.DatabaseContract.NAME_COLUMN + " text, " +
            Widgets.DatabaseContract.DATE_CREATED_COLUMN + " date DEFAULT current_timestamp)")
    void createTable();

    @Mapper(WidgetsResource.WidgetsMapper.class)
    @SqlQuery("Select * from " + Widgets.DatabaseContract.TABLE_NAME)
    List<WidgetDao> getWidgets();

    @SqlUpdate("insert into " + Widgets.DatabaseContract.TABLE_NAME +
            "(" + DatabaseContract.NAME_COLUMN + ") values (:widgetName)")
    void addWidget(@Bind("widgetName") String widgetName);
}

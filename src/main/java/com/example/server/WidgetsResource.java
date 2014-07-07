package com.example.server;

import com.codahale.metrics.annotation.Timed;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Path("/widgets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WidgetsResource {

    private final Widgets mWidgets;

    public WidgetsResource(Widgets widgets) {
        mWidgets = widgets;
    }

    @GET
    @Timed
    public List<WidgetDao> getWidgets() {
        return mWidgets.getWidgets();
    }

    @XmlRootElement
    private static final class Input {
        @XmlElement
        public String widgetName;
    }

    @POST
    @Consumes("application/json")
    @Path("/add")
    @Timed
    public List<WidgetDao> save(final Input input) {
        mWidgets.addWidget(input.widgetName);
        return getWidgets();
    }

    public static class WidgetsMapper implements ResultSetMapper<WidgetDao> {
        public WidgetDao map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            String widgetName = r.getString(Widgets.DatabaseContract.NAME_COLUMN);
            int widgetId = r.getInt(Widgets.DatabaseContract.ID_COLUMN);
            String widgetDateCreated = r.getString(Widgets.DatabaseContract.DATE_CREATED_COLUMN);
            return new WidgetDao(widgetId, widgetName, widgetDateCreated);
        }
    }
}

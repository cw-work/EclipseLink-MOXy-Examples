package example.json.demo3.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;

import net.madz.db.core.AbsSchemaMetaDataParser;
import net.madz.db.core.impl.DbOperatorFactoryImpl;
import net.madz.db.core.meta.immutable.mysql.MySQLColumnMetaData;
import net.madz.db.core.meta.immutable.mysql.MySQLForeignKeyMetaData;
import net.madz.db.core.meta.immutable.mysql.MySQLIndexMetaData;
import net.madz.db.core.meta.immutable.mysql.MySQLSchemaMetaData;
import net.madz.db.core.meta.immutable.mysql.MySQLTableMetaData;

@Singleton
@Path("/schema")
public class MySQLSchemaMetaDataList {

    private static volatile MySQLSchemaMetaData schemaMetaData = null;

    @SuppressWarnings("unchecked")
    @GET
    @Produces({ "application/json", "application/xml" })
    public MySQLSchemaMetaData get() throws SQLException {
        if ( null == schemaMetaData ) {
            synchronized (MySQLSchemaMetaDataList.class) {
                if ( null == schemaMetaData ) {
                    final AbsSchemaMetaDataParser<MySQLSchemaMetaData, MySQLTableMetaData, MySQLColumnMetaData, MySQLForeignKeyMetaData, MySQLIndexMetaData> sourceDbParser = DbOperatorFactoryImpl
                            .getInstance().createSchemaParser("fortest", false);
                    schemaMetaData = sourceDbParser.parseSchemaMetaData();
                }
            }
        }
        return schemaMetaData;
    }
}

package org.embulk.input.mysql;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Optional;
import org.embulk.config.TaskSource;
import org.embulk.input.jdbc.JdbcColumn;
import org.embulk.input.jdbc.JdbcColumnOption;
import org.embulk.input.jdbc.getter.ColumnGetterFactory;
import org.embulk.input.jdbc.getter.JsonColumnGetter;
import org.embulk.input.jdbc.getter.StringColumnGetter;
import org.embulk.input.mysql.getter.MySQLColumnGetterFactory;
import org.embulk.spi.type.Type;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class MySQLColumnGetterFactoryTest
{
    @Test
    public void testJsonSupported()
    {
        JdbcColumnOption mockColumnOption = new JdbcColumnOption()
        {
            @Override
            public String getValueType()
            {
                return "coalesce";
            }

            @Override
            public Optional<Type> getType()
            {
                return Optional.empty();
            }

            @Override
            public Optional<String> getTimestampFormat()
            {
                return Optional.empty();
            }

            @Override
            public Optional<String> getTimeZone()
            {
                return Optional.empty();
            }

            @Override
            public void validate()
            {

            }

            @Override
            @SuppressWarnings("deprecation")
            public TaskSource dump()
            {
                return null;
            }

            @Override
            public TaskSource toTaskSource()
            {
                return null;
            }

            @Override
            public ObjectNode toObjectNode()
            {
                return null;
            }
        };
        JdbcColumn jsonColumn = new JdbcColumn("any_column_name", "JSON", 1, 0, 0); // 1: CHAR

        ColumnGetterFactory fac = new MySQLColumnGetterFactory(null, null);
        assertThat(fac.newColumnGetter(null, null, jsonColumn, mockColumnOption), instanceOf(JsonColumnGetter.class));

        fac = new ColumnGetterFactory(null, null);
        assertThat(fac.newColumnGetter(null, null, jsonColumn, mockColumnOption), instanceOf(StringColumnGetter.class));
    }
}

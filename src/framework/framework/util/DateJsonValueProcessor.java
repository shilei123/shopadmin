package framework.util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import oracle.sql.TIMESTAMP;

import org.apache.log4j.Logger;

public class DateJsonValueProcessor implements JsonValueProcessor {
	private final static Logger LOG = Logger.getLogger(DateJsonValueProcessor.class);

	private String format;
	private SimpleDateFormat dataFormat;

	public DateJsonValueProcessor(String format) {
		this.format = format;
		this.dataFormat = new SimpleDateFormat(format);
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return null;
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		if (value == null) {
			return "";
		}
		if (value instanceof java.sql.Timestamp) {
			String str = dataFormat.format((java.sql.Timestamp) value);
			return str;
		}
		if (value instanceof java.util.Date) {
			String str = dataFormat.format((java.util.Date) value);
			return str;
		}
		if (value instanceof oracle.sql.TIMESTAMP) {
			String str = "";
			try {
				str = dataFormat.format(((TIMESTAMP)value).dateValue());
			} catch (SQLException e) {
				LOG.error(e);
			}
			return str;
		}

		return value.toString();
	}
}

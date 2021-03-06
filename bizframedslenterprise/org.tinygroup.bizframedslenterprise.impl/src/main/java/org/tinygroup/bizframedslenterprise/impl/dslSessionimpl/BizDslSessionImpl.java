package org.tinygroup.bizframedslenterprise.impl.dslSessionimpl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.RowMapperSelector;
import org.tinygroup.jdbctemplatedslsession.rowmapper.SimpleRowMapperSelector;
import org.tinygroup.tinysqldsl.ComplexSelect;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;

/**
 * DslSqlSession接口的jdbctemplate版实现
 * 
 * @author renhui
 * 
 */
public class BizDslSessionImpl implements DslSession {

	private JdbcTemplate jdbcTemplate;
	
	private RowMapperSelector selector = new SimpleRowMapperSelector();

	public BizDslSessionImpl(){
		super();
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public RowMapperSelector getSelector() {
		return selector;
	}

	public void setSelector(RowMapperSelector selector) {
		this.selector = selector;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public int execute(Insert insert) {
		return jdbcTemplate.update(insert.sql(), insert.getValues().toArray());
	}

	public int execute(Update update) {
		return jdbcTemplate.update(update.sql(), update.getValues().toArray());
	}

	public int execute(Delete delete) {
		return jdbcTemplate.update(delete.sql(), delete.getValues().toArray());
	}

	@SuppressWarnings("unchecked")
	public <T> T fetchOneResult(Select select, Class<T> requiredType) {
		return (T) jdbcTemplate.queryForObject(select.sql(), select.getValues()
				.toArray(), selector.rowMapperSelector(requiredType));
	}

	@SuppressWarnings("unchecked")
	public <T> T[] fetchArray(Select select, Class<T> requiredType) {
		List<T> records = fetchList(select, requiredType);
		if (!CollectionUtil.isEmpty(records)) {
			return (T[]) records.toArray();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> fetchList(Select select, Class<T> requiredType) {
		return jdbcTemplate.query(select.toString(), select.getValues()
				.toArray(), selector.rowMapperSelector(requiredType));
	}

	@SuppressWarnings("unchecked")
	public <T> T[] fetchArray(ComplexSelect complexSelect, Class<T> requiredType) {
		List<T> records = fetchList(complexSelect, requiredType);
		if (!CollectionUtil.isEmpty(records)) {
			return (T[]) records.toArray();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> fetchList(ComplexSelect complexSelect,
			Class<T> requiredType) {
		return jdbcTemplate.query(complexSelect.toString(), complexSelect
				.getValues().toArray(), selector
				.rowMapperSelector(requiredType));
	}

	@SuppressWarnings("unchecked")
	public <T> T fetchOneResult(ComplexSelect complexSelect,
			Class<T> requiredType) {
		return (T) jdbcTemplate.queryForObject(complexSelect.sql(),
				complexSelect.getValues().toArray(),
				selector.rowMapperSelector(requiredType));
	}

	public Number executeAndReturnKey(Insert insert) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T executeAndReturnObject(Insert insert) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T executeAndReturnObject(Insert insert, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T executeAndReturnObject(Insert insert, Class<T> clazz,
			boolean autoGeneratedKeys) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Pager<T> fetchPage(Select pageSelect, int start, int limit,
			boolean isCursor, Class<T> requiredType) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Pager<T> fetchCursorPage(Select pageSelect, int start,
			int limit, Class<T> requiredType) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Pager<T> fetchDialectPage(Select pageSelect, int start,
			int limit, Class<T> requiredType) {
		// TODO Auto-generated method stub
		return null;
	}

	public int count(Select select) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int[] batchInsert(Insert insert, List<Map<String, Object>> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchInsert(Insert insert, List<Map<String, Object>> params,
			int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchInsert(Insert insert, List<Map<String, Object>> params,
			int batchSize, boolean autoGeneratedKeys) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> int[] batchInsert(Insert insert, Class<T> requiredType,
			List<T> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> int[] batchInsert(Insert insert, Class<T> requiredType,
			List<T> params, int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> int[] batchInsert(Insert insert, Class<T> requiredType,
			List<T> params, int batchSize, boolean autoGeneratedKeys) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchUpdate(Update update, List<List<Object>> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchUpdate(Update update, Map<String, Object>[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> int[] batchUpdate(Update update, Class<T> requiredType,
			List<T> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchUpdate(Update update, List<List<Object>> params,
			int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchUpdate(Update update, Map<String, Object>[] params,
			int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> int[] batchUpdate(Update update, Class<T> requiredType,
			List<T> params, int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchDelete(Delete delete, List<List<Object>> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchDelete(Delete delete, Map<String, Object>[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> int[] batchDelete(Delete delete, Class<T> requiredType,
			List<T> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchDelete(Delete delete, List<List<Object>> params,
			int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] batchDelete(Delete delete, Map<String, Object>[] params,
			int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> int[] batchDelete(Delete delete, Class<T> requiredType,
			List<T> params, int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public int execute(Update update, boolean ignoreNull) {
		// TODO Auto-generated method stub
		return 0;
	}

}

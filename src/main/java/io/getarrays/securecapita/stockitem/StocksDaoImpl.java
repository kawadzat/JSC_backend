/**
 * kunal
 * SpringBootNamedParametreJdbcTemplate
 * com.org.kunal.parametrejdbc.stockitemnew
 */
package io.getarrays.securecapita.stockitem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Kumar.Kunal
 * SpringBootNamedParameterJdbcTemplate
 * 2023
 */
@Repository
@Slf4j
public class StocksDaoImpl implements StocksDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public StocksDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void save(Stocks stocks) {
        log.info("saveEmployee dao impl ---- '{}'", stocks);
        String sql = "INSERT INTO stocks (id ,username,password, department_requesting, stock_request_date ,department_code ,purpose_of_issue ,stock_date ," +
                "item_no ,item_reference_no , item_description ,date_of_previous_issue ,previous_issue_quantity,quantity_requested ,department_initiated_by," +
                "department_authorised_by , department_confirmed_by,department_received_by,designated_person_approval_name ,signature ,date_of_confirmation ,role )  " +
                "VALUES (:id, :username, :password, :department_requesting, :stock_request_date, :department_code, :purpose_of_issue, :stock_date, :item_no, " +
                ":item_reference_no," +
                ":item_description, :date_of_previous_issue, :previous_issue_quantity, :quantity_requested, :department_initiated_by, :department_authorised_by, " +
                ":department_confirmed_by, :department_received_by, :designated_person_approval_name, :signature, :date_of_confirmation, :role)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", stocks.getId());
        mapSqlParameterSource.addValue("username", stocks.getUsername());
        mapSqlParameterSource.addValue("password", stocks.getPassword());
        mapSqlParameterSource.addValue("department_requesting", stocks.getDepartmentRequesting());
        mapSqlParameterSource.addValue("stock_request_date", stocks.getStockRequestDate());
        mapSqlParameterSource.addValue("department_code", stocks.getDepartmentCode());
        mapSqlParameterSource.addValue("purpose_of_issue", stocks.getPurposeOfIssue());
        mapSqlParameterSource.addValue("stock_date", stocks.getStockDate());
        mapSqlParameterSource.addValue("item_no", stocks.getItemNo());
        mapSqlParameterSource.addValue("item_reference_no", stocks.getItemReferenceNo());
        mapSqlParameterSource.addValue("item_description", stocks.getItemDescription());
        mapSqlParameterSource.addValue("date_of_previous_issue", stocks.getDateOfPreviousIssue());
        mapSqlParameterSource.addValue("previous_issue_quantity", stocks.getPreviousIssueQuantity());
        mapSqlParameterSource.addValue("quantity_requested", stocks.getQuantityRequested());
        mapSqlParameterSource.addValue("department_initiated_by", stocks.getDepartmentInitiatedBy());
        mapSqlParameterSource.addValue("department_authorised_by", stocks.getDepartmentAuthorisedBy());
        mapSqlParameterSource.addValue("department_confirmed_by", stocks.getDepartmentConfirmedBy());
        mapSqlParameterSource.addValue("department_received_by", stocks.getDepartmentReceivedBy());
        mapSqlParameterSource.addValue("designated_person_approval_name", stocks.getDesignatedPersonApprovalName());
        mapSqlParameterSource.addValue("signature", stocks.getSignature());
        mapSqlParameterSource.addValue("date_of_confirmation", stocks.getDateOfConfirmation());
        mapSqlParameterSource.addValue("role", stocks.getRole());
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

        stocks.getStockRequests().forEach(stockRequest -> {
            log.info("saveStock dao impl in Stock Request for loop  ---- '{}'", stocks);
            saveStockRequest(stockRequest, stocks.getId());
        });
    }

    @Override
    public void update(Stocks stocks) {
        String updateSql = "UPDATE stocks SET item_description = :itemDescription, purpose_of_issue = :purposeOfIssue WHERE id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", stocks.getId());
        mapSqlParameterSource.addValue("purpose_of_issue", stocks.getPurposeOfIssue());
        mapSqlParameterSource.addValue("item_description", stocks.getItemDescription());
        namedParameterJdbcTemplate.update(updateSql, mapSqlParameterSource);
        String deleteSql = "DELETE FROM stock_request WHERE employee_id = :stockId";
        MapSqlParameterSource deleteParams = new MapSqlParameterSource();
        deleteParams.addValue("stockId", stocks.getId());
        namedParameterJdbcTemplate.update(deleteSql, deleteParams);
        stocks.getStockRequests().forEach(stockRequest -> saveStockRequest(stockRequest, stocks.getId()));
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM stocks WHERE id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public Stocks getById(int id) {
        String getByIdSql = "SELECT * FROM stocks WHERE id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(getByIdSql, mapSqlParameterSource, new StocksRowMapper());
    }

    @Override
    public List<Stocks> getAll() {
        String getAllSql = "SELECT * FROM stocks";
        return namedParameterJdbcTemplate.query(getAllSql, new StocksRowMapper());
    }

    private void saveStockRequest(StockRequest stockRequest, int stockId) {
        log.info("saveStock saveStockRequest dao impl ---- '{}'", stockId);
        String sql = "INSERT INTO stock_request (id, stock_id, start_date, end_date, status) "
                + "VALUES (:id, :stockId, :startDate, :endDate, :status)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", stockRequest.getId());
        mapSqlParameterSource.addValue("stockId", stockId);
        mapSqlParameterSource.addValue("startDate", stockRequest.getStartDate());
        mapSqlParameterSource.addValue("endDate", stockRequest.getEndDate());
        mapSqlParameterSource.addValue("status", stockRequest.getStatus());
        log.info("After saveStock saveStockRequest dao impl ---- '{}'", mapSqlParameterSource);
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

}

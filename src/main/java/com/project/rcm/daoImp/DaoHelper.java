/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.daoImp;

import com.project.common.Response;
import com.project.enums.ResponseStatus;
import com.project.jdbc.BasicConnectionPool;
import com.project.jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * @author hari
 */
public class DaoHelper {
    protected final Logger logger = java.util.logging.Logger.getLogger(DaoHelper.class.getName());

    final ConnectionPool connectionPool = BasicConnectionPool.getInstance();
    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Response buildResponse(ResponseStatus status, String message) {
        return new Response.ResponseBuilder()
                .withStatus(status)
                .withMessage(message)
                .build();
    }

    public void logError(Exception ex, String message) {
        logger.severe(String.format("%s: %s", message, ex.getMessage()));
    }

    public void logError(String errorCode, String message) {
        logger.severe(String.format("%s: %s", message, errorCode));
    }

    public void logSuccess(String message) {
        logger.info(message);
    }

    //private query methods
    public void handleClose(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            logError(ex, "Failed To Close Statement!");
        }
    }

    public <T> T runSql(String sql, Function<ResultSet, T> callback) {
        Statement stmt;
        ResultSet rs;
        Connection conn = this.connectionPool.getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            T ret = callback.apply(rs);
            handleClose(stmt);
            return ret;
        } catch (SQLException ex) {
            logger.severe(String.format("Failed To Get resultset %s", ex.getMessage()));
            return null;
        } finally {
            this.connectionPool.releaseConnection(conn);
        }
    }

    public Response updateSql(String sql) {
        Statement stmt;
        Connection conn = this.connectionPool.getConnection();
        try {
            stmt = conn.createStatement();
            Integer rs = stmt.executeUpdate(sql);
            handleClose(stmt);
            return buildResponse(ResponseStatus.SUCCESS, null);
        } catch (SQLException ex) {
            logger.severe(String.format("Failed To Get resultset %s", ex.getMessage()));
            return buildResponse(ResponseStatus.FAILURE, ex.getMessage());
        } finally {
            this.connectionPool.releaseConnection(conn);
        }
    }

    public String getDateAsString(Date d) {
        return format.format(d);
    }

}

package dal;

import java.sql.*;
import model.Deposit;

public class DepositDAO extends DBContext<Deposit> {

    public long insertDeposit(Deposit d) {
        String sql = """
            INSERT INTO Deposit 
            (status, deduction_amount, refund_amount, amount, payment_method, 
             deposit_date, refund_date, customer_id, booking_id, staff_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getStatus());
            ps.setObject(2, d.getDeductionAmount() == 0 ? null : d.getDeductionAmount(), Types.DECIMAL);
            ps.setObject(3, d.getRefundAmount() == 0 ? null : d.getRefundAmount(), Types.DECIMAL);
            ps.setObject(4, d.getAmount(), Types.DECIMAL);
            ps.setString(5, d.getPaymentMethod());
            ps.setTimestamp(6, d.getDepositDate());
            ps.setTimestamp(7, d.getRefundDate());
            ps.setObject(8, d.getCustomer() != null ? d.getCustomer().getUserId() : null, Types.BIGINT);
            ps.setObject(9, d.getBooking() != null ? d.getBooking().getBookingId() : null, Types.BIGINT);
            ps.setObject(10, d.getStaff() != null ? d.getStaff().getUserId() : null, Types.BIGINT);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getLong(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (Exception ignore) {}
        }
        return 0;
    }
}

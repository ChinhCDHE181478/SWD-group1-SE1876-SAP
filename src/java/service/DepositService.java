package service;

import dal.DepositDAO;
import model.Deposit;

public class DepositService {

    public long createDeposit(Deposit deposit) {
        DepositDAO depositDAO = new DepositDAO();
        return depositDAO.insertDeposit(deposit);
    }
}

package pl.edu.wszib.wallet.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.wallet.dao.IExpenseDAO;
import pl.edu.wszib.wallet.model.Expense;

import java.util.List;

@Repository
public class ExpenseDAOImpl implements IExpenseDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addNewExpense(Expense expense) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expense);
            tx.commit();
        }catch (Exception e){
            if (tx!=null){
                tx.rollback();

            }
        }finally {
            session.close();

        }

    }

    @Override
    public List<Expense> getExpensesByUserId(int id) {
        Session session = sessionFactory.openSession();
        Query<Expense> query = session.createQuery("FROM pl.edu.wszib.wallet.model.Expense WHERE user.id = :id");
        query.setParameter("id", id);
        List<Expense> expenseList = query.getResultList();
        session.close();
        return expenseList;

    }

}

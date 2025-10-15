package com.infy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.CustomerTransactionDTO;
import com.infy.dto.MonthlyRewardDTO;
import com.infy.dto.RewardResponseDTO;
import com.infy.entity.Transaction;
import com.infy.exception.RewardprogramCustomerException;
import com.infy.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RewardsService {
    @Autowired
    private TransactionRepository transactionRepository ;
 

    private final ModelMapper mapper = new ModelMapper();
 
 
   //1 Calculate the Price
  public static int calculate(double value) {
        int points = 0;
        if (value > 100) {
            points += (int) ((value - 100) * 2);
            points += 50;
           } else if (value > 50 && value <100) {
            points += (int) (value - 50);
            }
        return points;
          }



      public RewardResponseDTO calculateRewards(Long customerId, LocalDate startDate, LocalDate endDate) throws RewardprogramCustomerException {
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);

         if (transactions.isEmpty()) {
         throw new RewardprogramCustomerException("No transactions found for customer ID: " + customerId);      
 }

         RewardResponseDTO response = new RewardResponseDTO();
         response.setCustomerId(customerId);
         response.setCustomerName(transactions.get(0).getCustomerName());

         int totalPoints = 0;
            List<MonthlyRewardDTO> monthlyRewards = new ArrayList<>();
            List<CustomerTransactionDTO> transactionDTOs = new ArrayList<>();

         for (Transaction tx : transactions) {
            int points = calculate(tx.getAmount());
            totalPoints += points;

            String month = tx.getTransactionDate().getMonth().toString();


             Optional<MonthlyRewardDTO> existing = monthlyRewards.stream()
               .filter(m -> m.getMonth().equals(month)).findFirst();

                 if (existing.isPresent()) {
                  existing.get().setRewardPoints(existing.get().getRewardPoints() + points);
                 } else {
                monthlyRewards.add(new MonthlyRewardDTO(month, points));
             }

             CustomerTransactionDTO dto = mapper.map(tx, CustomerTransactionDTO.class);
            dto.setRewardPoints(points);
            transactionDTOs.add(dto);
            }
            response.setMonthlyRewards(monthlyRewards);
            response.setTotalRewards(totalPoints);
            response.setTransactions(transactionDTOs);
            return response;
        }

}

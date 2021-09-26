package com.betulsahin.loanapplicationservice.services;

@Service
@RequiredArgsConstructor
public class LoanTransactionLoggerServiceImpl implements LoanTransactionLoggerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanTransactionLoggerServiceImpl.class);

    private final ClientRequestInfo clientRequestInfo;
    private final LoanTransactionLoggerRepository loanTransactionLoggerRepository;

    @Override
    public void saveLoanTransaction(Loan loan) {
        LoanTransactionLogger transactionLogger = new LoanTransactionLogger();
        transactionLogger.setCustomerId(loan.getCustomer().getId());
        transactionLogger.setCreditAmount(loan.getCreditAmount());
        transactionLogger.setCreditResult(loan.getCreditResult());
        transactionLogger.setRequestDate(LocalDate.now());
        transactionLogger.setClientIpAdress(clientRequestInfo.getClientIpAdress());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());

        loanTransactionLoggerRepository.save(transactionLogger);

        LOGGER.info("Save transactions for loan {}", transactionLogger);
    }

    @Override
    public Page<List<LoanTransactionLogger>> getAllTransactionsWithDate(String transactionDate,
                                                                        Integer pageNumber,
                                                                        Integer pageSize,
                                                                        Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LoanValidator.validateTransactionDate(transactionDate, formatter);
        LocalDate transactionDateResult = LocalDate.parse(transactionDate, formatter);

        LOGGER.info("Validate transaction date {}", transactionDate);

        if (pageNumber != null && pageSize != null) {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByRequestDate(transactionDateResult, pageable);
    }

    @Override
    public Page<List<LoanTransactionLogger>> getAllByCustomerId(long customerId,
                                                                Integer pageNumber,
                                                                Integer pageSize,
                                                                Pageable pageable) {
        if (pageNumber != null && pageSize != null) {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByCustomerId(customerId, pageable);
    }

    @Override
    public Page<List<LoanTransactionLogger>> getAllByCreditResult(String creditResult,
                                                                  Integer pageNumber,
                                                                  Integer pageSize,
                                                                  Pageable pageable) {
        if (pageNumber != null && pageSize != null) {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByCreditResult(creditResult, pageable);
    }
}

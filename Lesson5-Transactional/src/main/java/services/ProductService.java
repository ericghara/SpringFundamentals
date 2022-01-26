package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional   // PlatformTransactionManager will only roll back on RuntimeExceptions (by default...)
    //@Transactional (noRollbackFor = RuntimeException.class, rollbackFor = Exception.class)  // rolls back checked exceptions but not runtime
    public void addOneProduct() {
        productRepository.addProduct("Milk", 3.69);
//        throw new RuntimeException("Example exception!");  // not exception must be propagated OUTSIDE the method (cannot be caught) so aop can intercept
    }

}

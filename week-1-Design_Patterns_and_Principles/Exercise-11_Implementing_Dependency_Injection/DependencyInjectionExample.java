public class DependencyInjectionExample {
    interface CustomerRepository {
        Customer findCustomerById(int id);
    }

    static class CustomerRepositoryImpl implements CustomerRepository {
        @Override
        public Customer findCustomerById(int id) {
            return new Customer(id, "Customer_" + id, "customer" + id + "@example.com");
        }
    }

    static class Customer {
        private int id;
        private String name;
        private String email;

        public Customer(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public String toString() {
            return "Customer [ID=" + id + ", Name=" + name + ", Email=" + email + "]";
        }
    }

    static class CustomerService {
        private CustomerRepository repository;

        public CustomerService(CustomerRepository repository) {
            this.repository = repository;
        }

        public void displayCustomer(int id) {
            Customer customer = repository.findCustomerById(id);
            System.out.println(customer);
        }
    }
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl(); 
        CustomerService service = new CustomerService(repository);   

        service.displayCustomer(101);
        service.displayCustomer(202);
    }
}

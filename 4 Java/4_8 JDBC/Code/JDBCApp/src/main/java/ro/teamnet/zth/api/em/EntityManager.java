public interface EntityManager {

    <T> T findById(Class<T> entityClass, Long id);

}

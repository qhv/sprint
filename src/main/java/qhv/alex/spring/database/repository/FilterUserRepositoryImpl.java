package qhv.alex.spring.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import qhv.alex.spring.database.entity.User;
import qhv.alex.spring.database.querydsl.QPredicates;
import qhv.alex.spring.dto.UserFilter;

import javax.persistence.EntityManager;
import java.util.List;

import static qhv.alex.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .buildAnd();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
//        var cb = entityManager.getCriteriaBuilder();
//        var criteria = cb.createQuery(User.class);
//        var user = criteria.from(User.class);
//        List<Predicate> predicates = new ArrayList<>();
//        if (filter.firstname() != null) {
//            predicates.add(cb.like(user.get("firstname"), filter.firstname()));
//        }
//        if (filter.lastname() != null) {
//            predicates.add(cb.like(user.get("lastname"), filter.lastname()));
//        }
//        if (filter.birthDate() != null) {
//            predicates.add(cb.lessThan(user.get("birthDate"), filter.birthDate()));
//        }
//        criteria.select(user).where(predicates.toArray(Predicate[]::new));
//
//        return entityManager.createQuery(criteria).getResultList();
    }
}

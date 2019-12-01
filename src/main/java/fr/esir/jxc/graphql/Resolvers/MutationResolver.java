package fr.esir.jxc.graphql.Resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import fr.esir.jxc.elasticsearch.repositories.UserRepository;
import fr.esir.jxc.graphql.exceptions.GraphqlServerSideException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;

    public User createUser(User user){
        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword()) || user.getAddress() == null){
            throw new GraphqlServerSideException("Unsupported value. In order to create a User, you must have an email, an username, a password and an address");
        }
        return userRepository.save(user);
    }

    public Article createArticle(Article article){
        if (StringUtils.isEmpty(article.getId()) || StringUtils.isEmpty(article.getUrl())){
            throw new GraphqlServerSideException("Unsupported value. In order to create an Article, you must have an id and an url");
        }
        return articleRepository.save(article);
    }
}

package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void insert(String title, String content, String author){
        Query query = em.createNativeQuery("insert into board_tb(title, content, author) values(?, ?, ?)");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, author);

        query.executeUpdate();
    }

    public Board selectOne(int id){
        Query query = em.createNativeQuery("select * from board_tb where id=?",Board.class);
        query.setParameter(1,id);

        try {
            Board board = (Board) query.getSingleResult();

            return board;
        } catch (Exception e) {
            return null ;
        }

    }

    public List<Board> selectAll(){
        Query query = em.createNativeQuery("select * from board_tb",Board.class);
        List<Board> boardList = query.getResultList(); // 테스트 하려면 더미데이터 없이 테스트 해봐야됨. 데이터가 없을 때 어떻게 되는지.
        //        //더미데이터가 0이면 boardList 의 사이즈는 0. 빈 컬렉션을 리턴.
        return boardList ;
    }
    @Transactional
    public void delete(int id){
        Query query = em.createNativeQuery("delete from board_tb where id =?");
        query.setParameter(1,id);
        query.executeUpdate();
    }
    @Transactional
    public void updateTitle(String title,int id){
        Query query = em.createNativeQuery("update board_tb set title =? where id =?");
        query.setParameter(1,title);
        query.setParameter(2,id);
        query.executeUpdate();
    }

    @Transactional
    public void updateContent(String content,int id){
        Query query = em.createNativeQuery("update board_tb set content =? where id =?");
        query.setParameter(1,content);
        query.setParameter(2,id);
        query.executeUpdate();
    }

    @Transactional
    public void updateAuthor(String author,int id){
        Query query = em.createNativeQuery("update board_tb set author =? where id =?");
        query.setParameter(1,author);
        query.setParameter(2,id);
        query.executeUpdate();
    }



}

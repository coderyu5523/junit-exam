package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import java.util.List;

@Import(BoardRepository.class) //내가 만든 클래스는 import 해야됨.
@DataJpaTest //DB관련 객체들이 HEAP에 뜬다. 적어야 테스트 가능.
public class BoardRepositoryTest {


    @Autowired // 테스트에서 DI 하는 코드
    private BoardRepository boardRepository;
    @Test
    public void insert_test(){  // 테스트 메서드는 파라미터를 적을 수 없음. 리턴도 없음.
        //given 가짜 데이터 넣기
        String title ="제목10";
        String content = "내용10";
        String author = "이순신";


        //when
        boardRepository.insert(title,content,author);


        //then  확인

        List<Board> boardList = boardRepository.selectAll();
        System.out.println(boardList.size());

        //롤백(자동)
    }

    @Test
    public void select_test(){
       //given
       int id =1;

       //when
        Board board = boardRepository.selectOne(id);

       //then. 20이 들어오면 컨트롤러가 아니라 레파지토리 책임, 상태 검사 객체의 필트
//        System.out.println(board);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목2");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
        Assertions.assertThat(board.getAuthor()).isEqualTo("홍길동");
    }
    @Test
    public void selectAll_test(){
        //given


        //when
        List<Board>  boardList =  boardRepository.selectAll();

        //then
        System.out.println(boardList.size());
        //더미데이터가 0이면 boardList 의 사이즈는 0. 빈 컬렉션을 리턴.
        Assertions.assertThat(boardList.get(0).getTitle()).isEqualTo("제목1");
        Assertions.assertThat(boardList.get(0).getContent()).isEqualTo("내용1");
        Assertions.assertThat(boardList.get(0).getAuthor()).isEqualTo("홍길동");

        Assertions.assertThat(boardList.size()).isEqualTo(8);

    }
    @Test
    public void delete_test(){
        //given
        int id1 =1 ;
        int id2 = 2;

        //when
        boardRepository.delete(id1);

        //then

        Board board1 = boardRepository.selectOne(id1);
        Board board2 = boardRepository.selectOne(id2);
        List<Board>  boardList =  boardRepository.selectAll();


        System.out.println(board1);
        System.out.println(board2);
        System.out.println(boardList.size());

    }
    @Test
    public void updateTitle_test(){
        //given;
        String title = "수정1";
        int id = 1;


        //when
        boardRepository.updateTitle(title,id);

        //then
        Board board = boardRepository.selectOne(id);

        System.out.println(board);

        Assertions.assertThat(board.getTitle()).isEqualTo("수정1");


    }





}

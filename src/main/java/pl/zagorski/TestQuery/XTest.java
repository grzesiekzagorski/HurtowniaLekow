package pl.zagorski.TestQuery;

import pl.zagorski.domain.Position;
import pl.zagorski.repositories.PositionRepositoryImpl;

import java.util.List;

public class XTest {
    public static void main(String[]args){
        PositionRepositoryImpl positionRepository = new PositionRepositoryImpl();

       List<Position> lista = positionRepository.orderByName();

       for(Position x : lista){
           System.out.println(x.getName());
       }


    }
}

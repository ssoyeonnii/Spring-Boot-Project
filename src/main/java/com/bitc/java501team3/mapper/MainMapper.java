package com.bitc.java501team3.mapper;

import com.bitc.java501team3.dto.FavListDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ApiDbDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    void insertFav(int favListStoreIdx,String loggedInUserId);

    void deleteFav(int favListStoreIdx,String loggedInUserId);

    List<FavListDTO> selectFavList(String loggedInUserId);

    List<ApiDbDTO> getCard() throws Exception;

    List<ApiDbDTO> searchGetCard(String keyword) throws Exception;
}

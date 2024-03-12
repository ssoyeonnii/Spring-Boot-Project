package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.FavListDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ApiDbDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ItemDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.RootDTO;

import java.util.List;

public interface MainService {
    List<ItemDTO> gethotPlaceJson(String serviceUrl) throws Exception;

    void insertFav(int buttonIndex,String loggedInUserId)throws Exception;

    void deltetFav(int favListStoreIdx,String loggedInUserId);

    List<FavListDTO> selectFavList(String loggedInUserId);

    List<ApiDbDTO> getCard() throws Exception;

    List<ApiDbDTO> searchGetCard(String keyword) throws Exception;
}

package com.yunzhidata.jiushuo.website.util;

import com.yunzhidata.jiushuo.website.dto.ImageDto;
import com.yunzhidata.jiushuo.website.input.ImgInputType;

import java.io.File;

public interface UtilService {

    File getSaveFileName(String endName);
    ImageDto imgDispatch(ImgInputType input);
    File isFile(String url);
}

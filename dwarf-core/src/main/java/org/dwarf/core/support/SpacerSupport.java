package org.dwarf.core.support;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/26 11:30
 * @Description:
 **/
@RequiredArgsConstructor
public class SpacerSupport {
    @Getter
    @Setter
    private List<String> message = new ArrayList<>();

    @Getter
    @Setter
    private List<String> expression = new ArrayList<>();

    @NonNull
    private final String startParse;
    @NonNull
    private final String endParse;

    public void doParse(String parseStr) {
        int indexTemp = -1, indexStart = -1, startTemp = 0, endTemp = 0;
        while (indexStart < parseStr.length()) {
            indexTemp = indexStart + 1;
            if ((indexStart = startTemp = parseStr.indexOf(startParse, indexStart + 1)) == -1) {
                break;
            }
            if ((indexStart = endTemp = parseStr.indexOf(endParse, indexStart + 1)) == -1) {
                break;
            }
            message.add(parseStr.substring(indexTemp, startTemp));
            expression.add(this.handleSpel(parseStr.substring(startTemp, endTemp + 1)));
        }
        message.add(parseStr.substring(indexTemp));
    }

    private String handleSpel(String spel) {
        return spel.substring(startParse.length(), spel.length() - startParse.length())
                .trim();
    }

}

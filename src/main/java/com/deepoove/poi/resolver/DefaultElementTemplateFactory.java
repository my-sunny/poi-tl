/*
 * Copyright 2014-2020 Sayi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deepoove.poi.resolver;

import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.template.ChartTemplate;
import com.deepoove.poi.template.PictureTemplate;
import com.deepoove.poi.template.run.RunTemplate;

/**
 * @author Sayi
 */
public class DefaultElementTemplateFactory implements ElementTemplateFactory {

    public static final char EMPTY_CHAR = '\0';
    private final Configure config;

    public DefaultElementTemplateFactory(Configure config) {
        this.config = config;
    }

    @Override
    public RunTemplate createRunTemplate(String tag, XWPFRun run) {
        RunTemplate template = new RunTemplate();
        Set<Character> gramerChars = config.getGramerChars();
        Character symbol = Character.valueOf(EMPTY_CHAR);
        if (!"".equals(tag)) {
            char fisrtChar = tag.charAt(0);
            for (Character chara : gramerChars) {
                if (chara.equals(fisrtChar)) {
                    symbol = Character.valueOf(fisrtChar);
                    break;
                }
            }
        }
        template.setSource(config.getGramerPrefix() + tag + config.getGramerSuffix());
        template.setTagName(symbol.equals(Character.valueOf(EMPTY_CHAR)) ? tag : tag.substring(1));
        template.setSign(symbol);
        template.setRun(run);
        return template;
    }

    @Override
    public PictureTemplate createPicureTemplate(String tag, XWPFPicture pic) {
        PictureTemplate template = new PictureTemplate();
        template.setSource(config.getGramerPrefix() + tag + config.getGramerSuffix());
        template.setTagName(tag);
        template.setSign(EMPTY_CHAR);
        template.setPicture(pic);
        return template;
    }

    @Override
    public ChartTemplate createChartTemplate(String tag, XWPFChart chart, XWPFRun run) {
        ChartTemplate template = new ChartTemplate();
        template.setSource(config.getGramerPrefix() + tag + config.getGramerSuffix());
        template.setTagName(tag);
        template.setSign(EMPTY_CHAR);
        template.setChart(chart);
        template.setRun(run);
        return template;
    }

}
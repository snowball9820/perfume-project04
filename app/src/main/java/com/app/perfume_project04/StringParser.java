package com.app.perfume_project04;

public class StringParser {

    public ParsedData parse(String text) {
        ParsedData parsedData = new ParsedData();

        // 문자열을 파싱하여 필요한 정보 추출
        String[] parts = text.split("\\|");

        for (String part : parts) {
            String[] keyValue = part.split(":");

            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                // 각 항목에 따라 적절한 처리 수행
                switch (key) {
                    case "Name":
                        parsedData.setName(value);
                        break;
                    case "Brand":
                        parsedData.setBrand(value);
                        break;
                    case "Gender":
                        parsedData.setGender(value);
                        break;
                    case "Rating":
                        parsedData.setRating(Double.parseDouble(value));
                        break;
                    case "Notes":
                        parsedData.setNotes(value);
                        break;
                    case "Perfume URL":
                        parsedData.setUrl(value);
                        break;
                }
            }
        }

        return parsedData;
    }

}

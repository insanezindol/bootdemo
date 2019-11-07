package com.example.bootdemo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.bootdemo.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class ApiService {

    public void callUpxide() {
        String API_URL_TICKER = "https://www.upxide.com/trade/home-market-price";
        String API_URL_ORDERBOOK = "https://www.upxide.com/trade/trade?symbol=KRW_";
        String[] ASSET_ARR = {"BTC", "ETH", "BCH", "ETC", "ADA", "USD", "OST"};
        //String[] ASSET_ARR = {"BCH"};

        String tickerPlainTxt = HttpUtil.get(API_URL_TICKER);
        JSONArray tickerJson = JSONArray.parseArray(tickerPlainTxt);

        for (int i = 0; i < tickerJson.size(); i++) {
            String currency = tickerJson.getJSONObject(i).getString("k");

            if (currency.equals("KRW")) {
                JSONArray assetArr = tickerJson.getJSONObject(i).getJSONArray("l");

                for (int j = 0; j < ASSET_ARR.length; j++) {

                    for (int k = 0; k < assetArr.size(); k++) {
                        JSONObject assetObj = assetArr.getJSONObject(k);
                        String asset = assetObj.getString("s");
                        BigDecimal marketPrice = assetObj.getBigDecimal("c");
                        BigDecimal volumn = assetObj.getBigDecimal("v24");

                        String arrTxt = "";
                        if (ASSET_ARR[j].equals("USD")) {
                            arrTxt = "USDT";
                        } else {
                            arrTxt = ASSET_ARR[j];
                        }

                        if (asset.equals("KRW_" + arrTxt)) {
                            String orderbookPlainTxt = HttpUtil.get(API_URL_ORDERBOOK + arrTxt);
                            JSONObject orderbookJson = JSONObject.parseObject(orderbookPlainTxt);

                            BigDecimal bid = new BigDecimal(0);
                            try {
                                JSONArray bidArr = orderbookJson.getJSONArray("buy");
                                int bidArrLoopSize = 10;
                                if (bidArrLoopSize > bidArr.size()) {
                                    bidArrLoopSize = bidArr.size();
                                }
                                for (int l = 0; l < bidArrLoopSize; l++) {
                                    BigDecimal bidQuantity = bidArr.getJSONArray(l).getBigDecimal(1);
                                    bid = bid.add(bidQuantity);
                                }
                            } catch (Exception e) {
                                log.error("buy is null");
                            }

                            BigDecimal ask = new BigDecimal(0);
                            try {
                                JSONArray askArr = orderbookJson.getJSONArray("sell");
                                int askArrLoopSize = 10;
                                if (askArrLoopSize > askArr.size()) {
                                    askArrLoopSize = askArr.size();
                                }
                                for (int l = 0; l < askArrLoopSize; l++) {
                                    BigDecimal askQuantity = askArr.getJSONArray(l).getBigDecimal(1);
                                    ask = ask.add(askQuantity);
                                }
                            } catch (Exception e) {
                                log.error("sell is null");
                            }

                            BigDecimal ratio1 = ask.add(bid);
                            BigDecimal ratio2 = ratio1.multiply(marketPrice);
                            BigDecimal ratio = volumn.divide(ratio2, 2, BigDecimal.ROUND_HALF_UP);

                            ask = ask.setScale(4, BigDecimal.ROUND_HALF_UP);
                            bid = bid.setScale(4, BigDecimal.ROUND_HALF_UP);

                            log.info(ASSET_ARR[j] + "/KRW");
                            log.info("" + marketPrice);
                            log.info(ask.toString());
                            log.info(bid.toString());
                            log.info("" + volumn);
                            log.info("" + ratio);
                        }

                    }

                }

            }

        }

    }

}

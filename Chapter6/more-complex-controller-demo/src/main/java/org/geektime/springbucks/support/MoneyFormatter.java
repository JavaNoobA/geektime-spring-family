package org.geektime.springbucks.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author pengfei.zhao
 * @date 2020/7/30 14:41
 */
@Component
public class MoneyFormatter implements Formatter<Money> {
    /**
     * 处理 CNY 10.00 / 10.00 形式的字符串
     * 校验不太严密，仅作演示
     */
    @Override
    public Money parse(String s, Locale locale) throws ParseException {
        if (NumberUtils.isParsable(s)){
            return Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(s));
        }else if (StringUtils.isNotEmpty(s)){
            String[] currencyMoney = StringUtils.split(s, " ");
            if (currencyMoney != null && currencyMoney.length == 2 && NumberUtils.isParsable(currencyMoney[1])){
                return Money.of(CurrencyUnit.of(currencyMoney[0]), NumberUtils.createBigDecimal(currencyMoney[1]));
            }
            throw new ParseException(s, 0);
        }
        throw new ParseException(s, 0);
    }

    @Override
    public String print(Money money, Locale locale) {
        return money.getCurrencyUnit() + " " + money.getAmount();
    }
}

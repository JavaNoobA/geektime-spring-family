package org.geektime.springbucks.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.geektime.springbucks.controller.request.NewCoffeeRequest;
import org.geektime.springbucks.model.Coffee;
import org.geektime.springbucks.service.CoffeeService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pengfei.zhao
 * @date 2020/7/30 15:53
 */
@RestController
@RequestMapping("/coffee")
@Slf4j
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addCoffee(@Valid @RequestBody NewCoffeeRequest coffeeRequest,
                            BindingResult result){
        if (result.hasErrors()){
            // TODO 暂不处理
            log.error("Add Coffee: {}", result.getAllErrors());
            return null;
        }
        return coffeeService.addCoffee(coffeeRequest.getName(), coffeeRequest.getPrice());
    }

    /***
     * 根据上传的文件 解析对应的 coffee 数据
     * @param file 上传的文件
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Coffee> batchAdd(@RequestParam("file")MultipartFile file){
        List<Coffee> coffees = new ArrayList<>();
        if (!file.isEmpty()){
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String str;
                while ((str = reader.readLine()) != null){
                    String[] arr = StringUtils.split(str, " ");
                    if (arr != null && arr.length == 2){
                        coffees.add(coffeeService.addCoffee(arr[0],
                                Money.of(CurrencyUnit.of("CNT"), NumberUtils.createBigDecimal(arr[1]))));
                    }
                }
            }catch (IOException e){
                log.error("read file error: {}", e.getMessage());
            }finally {
                IOUtils.closeQuietly(reader);
            }
        }
        return coffees;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Coffee getById(@PathVariable("id") long id){
        return coffeeService.getCoffee(id);
    }

    @GetMapping
    public Coffee getByName(@RequestParam("name") String name){
        return coffeeService.getCoffee(name);
    }

    @GetMapping(path = "/", params = "!name")
    public List<Coffee> getAllCoffee(){
        return coffeeService.getAllCoffee();
    }
}

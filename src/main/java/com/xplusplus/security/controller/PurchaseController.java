package com.xplusplus.security.controller;

import com.xplusplus.security.domain.Purchase;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.PurchaseService;
import com.xplusplus.security.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午7:02:30 2018年5月22日
 */
@RestController
@RequestMapping(value = "/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	/**
	 * 新增
	 * 
	 * @param purchase
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<Purchase> add(@Valid Purchase purchase, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(purchaseService.save(purchase));
	}

	/**
	 * 更新
	 * 
	 * @param purchase
	 * @return
	 */
	@RequestMapping(value = "/update")
	public Result<Purchase> update(@Valid Purchase purchase, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(purchaseService.update(purchase));
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	public Result<Object> deleteById(Long id) {
		purchaseService.delete(id);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 * 
	 * @param purchases
	 * @return
	 */
	@RequestMapping(value = "/deleteByIdBatch")
	public Result<Object> deleteByIdBatch(@RequestBody Collection<Purchase> purchases) {
		purchaseService.deleteInBatch(purchases);
		return ResultUtil.success();
	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById")
	public Result<Purchase> getById(Long id) {
		return ResultUtil.success(purchaseService.findOne(id));
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public Result<List<Purchase>> getAll() {
		return ResultUtil.success(purchaseService.findAll());

	}

	/**
	 * 查询所有-分页
	 * 
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getAllByPage")
	public Result<Page<Purchase>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(purchaseService.findAllByPage(page, size, sortFieldName, asc));
	}
}

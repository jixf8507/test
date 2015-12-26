package com.qx.eakay.service.price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.eakay.dao.price.PriceTypeRelationDao;
import com.qx.eakay.model.price.CarPriceTypeRelationPO;
import com.qx.eakay.service.BaseService;

@Service
public class PriceTypeRelationService extends BaseService {

	@Autowired
	private PriceTypeRelationDao priceTypeRelationDao;

	/**
	 * 根据车辆id查找租赁套餐列表
	 * 
	 * @param carId
	 * @param tableName
	 * @param merchantId
	 * @return
	 */
	public List<Map<String, Object>> findPriceByCarId(JSONObject jsonObject) {

		if ("innerSelect".equals(jsonObject.get("type"))) {
			return priceTypeRelationDao.findByCarId(jsonObject);
		}
		if ("leftSelect".equals(jsonObject.get("type"))) {
			return priceTypeRelationDao.findAllByCarId(jsonObject);
		}
		return null;
	}

	/**
	 * 保存车辆租赁收费套餐
	 * 
	 * @param carId
	 *            车辆ID
	 * @param priceId
	 *            收费ID(多个以","号隔开)
	 * @return
	 */
	@Transactional
	public boolean saveRelation(Integer carId, Integer[] typeId,
			String tableName) {
		// 删除车辆原有的收费关系
		priceTypeRelationDao.deleteRelationsByCarId(carId, tableName);

		// 批量添加车辆新的收费关系
		List<CarPriceTypeRelationPO> relations = new ArrayList<>();
		if (typeId != null) {
			for (Integer pid : typeId) {
				CarPriceTypeRelationPO relationPO = new CarPriceTypeRelationPO();
				relationPO.setPriceTypeId(pid);
				relationPO.setCarId(carId);
				relations.add(relationPO);
			}
		}
		return priceTypeRelationDao.batchSaveRelation(relations, tableName);
	}

	@Transactional
	public boolean savePerceRelation(Integer priceId, List<Integer> carId,
			String tableName) {
		// 删除车辆原有的收费关系
		priceTypeRelationDao.deleteRelationsByPriceId(priceId, tableName);

		// 批量添加车辆新的收费关系
		List<CarPriceTypeRelationPO> relations = new ArrayList<>();
		for (Integer cid : carId) {
			CarPriceTypeRelationPO relationPO = new CarPriceTypeRelationPO();
			relationPO.setPriceTypeId(priceId);
			relationPO.setCarId(cid);
			relations.add(relationPO);
		}
		return priceTypeRelationDao.batchSaveRelation(relations, tableName);
	}

}

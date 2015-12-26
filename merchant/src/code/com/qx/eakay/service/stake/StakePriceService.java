package com.qx.eakay.service.stake;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.stake.StakeDeviceNameDao;
import com.qx.eakay.dao.stake.StakePriceDao;
import com.qx.eakay.export.stake.StakePriceExport;
import com.qx.eakay.model.stake.StakeDeviceNamePO;
import com.qx.eakay.model.stake.StakePricePO;
import com.qx.eakay.service.BaseService;

/**
 * 充电套餐
 * 
 */
@Service
public class StakePriceService extends BaseService {

	@Autowired
	private StakePriceDao stakePriceDao;
	@Autowired
	private StakeDeviceNameDao deviceNameDao;

	/**
	 * 分页查询充电套餐信息
	 * 
	 * @param jsonObject
	 * @param getiDisplayLength
	 * @param getiDisplayStart
	 * @return
	 */
	public PageResults findPricePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return stakePriceDao.findPricePage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索充电套餐信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findPriceList(JSONObject jsonObject) {
		return stakePriceDao.findPriceList(jsonObject);
	}

	/**
	 * 导出充电套餐信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportPrice(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new StakePriceExport();
		List<Map<String, Object>> list = findPriceList(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增充电套餐
	 * 
	 * @param stakePricePO
	 * @return
	 */
	public boolean createPrice(StakePricePO stakePricePO) {
		getTime(stakePricePO);
		return stakePriceDao.createPrice(stakePricePO);
	}

	/**
	 * 时间转换
	 * 
	 * @param stakePricePO
	 * @return
	 */
	private StakePricePO getTime(StakePricePO stakePricePO) {
		String[] hm1 = stakePricePO.getTime1().substring(11).split(":");
		String[] hm2 = stakePricePO.getTime2().substring(11).split(":");
		String[] hm3 = stakePricePO.getTime3().substring(11).split(":");
		String[] hm4 = stakePricePO.getTime4().substring(11).split(":");
		stakePricePO.setHour1(hm1[0]);
		stakePricePO.setHour2(hm2[0]);
		stakePricePO.setHour3(hm3[0]);
		stakePricePO.setHour4(hm4[0]);
		stakePricePO.setMinute1(hm1[1]);
		stakePricePO.setMinute2(hm2[1]);
		stakePricePO.setMinute3(hm3[1]);
		stakePricePO.setMinute4(hm4[1]);
		return stakePricePO;
	}

	/**
	 * 删除充电套餐
	 * 
	 * @param id
	 * @return
	 */
	public boolean deletePrice(Integer id) {
		return stakePriceDao.deletePrice(id);
	}

	/**
	 * 判断充电套餐名称是否唯一
	 * 
	 * @param name
	 * @param id
	 * @param merchantId
	 * @return
	 */
	public boolean checkName(String name, Integer id, Integer merchantId) {
		StakePricePO stakePricePO = stakePriceDao.getStakePriceByName(name,
				merchantId);
		if (stakePricePO == null) {
			return true;
		}
		if (id.equals(stakePricePO.getId())) {
			return true;
		}
		return false;
	}

	/**
	 * 查找充电套餐
	 * 
	 * @param id
	 * @return
	 */
	public StakePricePO getStakePrice(Integer id) {
		return stakePriceDao.getStakePrice(id);
	}

	/**
	 * 修改充电套餐
	 * 
	 * @param stakePricePO
	 * @return
	 */
	@Transactional
	public boolean editePrice(StakePricePO stakePricePO) {
		// 获取修改前套餐信息
		StakePricePO stakePrice = stakePriceDao.getStakePrice(stakePricePO
				.getId());
		getTime(stakePricePO);

		// 判断套餐是否已绑定充电桩
		StakeDeviceNamePO stakeDeviceNamePO = deviceNameDao
				.getStakePriceByPriceId(stakePricePO.getId());
		if (stakeDeviceNamePO != null) {
			// 判断是否修改了套餐
			if (stakePrice.getHour1().equals(stakePricePO.getHour1())
					&& stakePrice.getHour2().equals(stakePricePO.getHour2())
					&& stakePrice.getHour3().equals(stakePricePO.getHour3())
					&& stakePrice.getHour4().equals(stakePricePO.getHour4())
					&& stakePrice.getMinute1()
							.equals(stakePricePO.getMinute1())
					&& stakePrice.getMinute2()
							.equals(stakePricePO.getMinute2())
					&& stakePrice.getMinute3()
							.equals(stakePricePO.getMinute3())
					&& stakePrice.getMinute4()
							.equals(stakePricePO.getMinute4())
					&& stakePrice.getPriceA().equals(stakePricePO.getPriceA())
					&& stakePrice.getPriceB().equals(stakePricePO.getPriceB())
					&& stakePrice.getPriceC().equals(stakePricePO.getPriceC())
					&& stakePrice.getPriceD().equals(stakePricePO.getPriceD())) {
				logger.info("------->充电费率未修改");
			} else {
				logger.info("------->充电费率已修改，重置下发费率为未下发");
				stakePriceDao.resetPrice(stakePricePO);
			}
		}

		// 修改套餐
		return stakePriceDao.editePrice(stakePricePO);
	}

	/**
	 * 保存充电套餐选择充电桩
	 * 
	 * @param id
	 * @param sids
	 *            现在
	 * @param sids1
	 *            原来
	 * @return
	 */
	@Transactional
	public boolean savePriceStake(Integer id, List<Integer> sids,
			List<Integer> sids1) {
		// 查找充电桩原有的收费关系

		// 删除充电桩原有的收费关系
		stakePriceDao.deleteRelationsByPriceId(id);
		// 批量添加充电桩新的收费关系
		return stakePriceDao.batchSavePriceStake(id, sids);
	}

	/**
	 * 保存充电桩选择充电套餐
	 * 
	 * @param id
	 * @param stakeId
	 * @return
	 */
	public boolean saveStakePrice(Integer id, List<Integer> sids) {
		// 批量添加充电桩新的收费关系
		return stakePriceDao.batchSavePriceStake(id, sids);
	}

}

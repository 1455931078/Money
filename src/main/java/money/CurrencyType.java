package money;

/**
 * 货币种类. (Money 1.x 版本时货币种类为 boolean, 2.0 之后修改为 enum)
 * 获取 String 值方法: {@link MoneyAPI#getCurrency(CurrencyType)}
 * 此枚举复写了 {@link Object#toString()}, 该方法返回值将变为指定货币在 "Config.yml" 中配置的值如 "Coin"
 * <br/>
 * Currency type
 * The way to get String value: {@link MoneyAPI#getCurrency(CurrencyType)}
 * The enum is overridden {@link Object#toString()}, This method will return the value in "Config.yml" like "Coin"
 *
 * @author Him188 @ Money Project
 */
public enum CurrencyType {
	/**
	 * 货币 1.
	 * 可在 "Config.yml" 中修改 (项目 "money-unit-1")
	 * 默认值为 "Coin"
	 * <br/>
	 * The first currency type
	 * It can be modified in "Config.yml" (Section "money-unit-1")
	 * Default value is "Coin"
	 */
	FIRST(false),

	/**
	 * 货币 2.
	 * 可在 "Config.yml" 中修改 (项目 "money-unit-2")
	 * 默认值为 "Point"
	 * <br/>
	 * The second currency type
	 * It can be modified in "Config.yml" (Section "money-unit-2")
	 * Default value is "Point"
	 */
	SECOND(true);


	private boolean booleanValue;

	CurrencyType(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public boolean booleanValue() {
		return booleanValue;
	}

	@Override
	public String toString() {
		return MoneyAPI.getInstance().getCurrency(this);
	}

	/**
	 * 将逻辑值转换为货币类型
	 * Convert boolean value to {@link CurrencyType}
	 *
	 * @param booleanValue 逻辑值 | boolean
	 *
	 * @return 货币类型. CurrencyType
	 */
	public static CurrencyType fromBoolean(boolean booleanValue) {
		return booleanValue ? SECOND : FIRST;
	}

	/**
	 * 将货币在 Config.yml 中的配置值 (如 "Coin")转换为货币类型
	 * Convert currency name in Config.yml to {@link CurrencyType}
	 *
	 * @param value 货币在 Config.yml 中的配置值 | currency name in Config.yml
	 *
	 * @return 货币类型 | CurrencyType
	 */
	public static CurrencyType fromString(String value) {
		if (value.equals(MoneyAPI.getInstance().getCurrency1())) {
			return fromBoolean(false);
		} else if (value.equals(MoneyAPI.getInstance().getCurrency2())) {
			return fromBoolean(true);
		}

		return null;
	}
}

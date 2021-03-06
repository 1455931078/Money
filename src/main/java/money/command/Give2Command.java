package money.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import money.CurrencyType;
import money.Money;
import money.Utils;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author Him188 @ Money Project
 */
public class Give2Command extends MoneyCommand {
    public Give2Command(String name, Money owner, String[] aliases) {
        super(name, owner, aliases);
        this.setPermission("money.command.give2");
        this.setCommandParameters(new HashMap<String, CommandParameter[]>() {
            {
                put("give-2", new CommandParameter[]{
                        new CommandParameter("player", CommandParameter.ARG_TYPE_STRING, false),
                        new CommandParameter("amount", CommandParameter.ARG_TYPE_INT, false)
                });
            }
        });
    }

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!this.testPermission(sender)) {
			//sender.sendMessage(this.getPlugin().translateMessage("has-no-permission"));
			return true;
		}


		if (args.length < 2) {
			sender.sendMessage(this.getPlugin().translateMessage("give-format-error", "cmd", this.getName()));
			return true;
		}

		float to = Float.parseFloat(args[1]);

		Player p = Utils.getPlayer(args[0]);
		String name;
		if (p == null) {
			name = args[0];
		} else {
			name = p.getName();
		}

		if (Objects.equals(name, "")) {
			sender.sendMessage(getPlugin().translateMessage("invalid-name", "cmd", this.getName()));
			return true;
		}

		if (!getPlugin().addMoney(name, to, CurrencyType.SECOND)) {
			sender.sendMessage(getPlugin().translateMessage("give-failed",
                    "amount", to,
                    "type", getPlugin().getCurrency2(),
					"name", name));
			return true;
		}

		if (p != null) {
			p.sendMessage(getPlugin().translateMessage("give-done",
					"name", sender.getName(),
                    "amount", to,
                    "type", getPlugin().getCurrency2()));
		}
		sender.sendMessage(getPlugin().translateMessage("give-success",
                "amount", to,
                "type", getPlugin().getCurrency2(),
				"name", name));
		return true;
	}
}

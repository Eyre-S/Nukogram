package cc.sukazyo.nukos.feature;

import androidx.annotation.NonNull;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.Components.Bulletin;

import java.util.regex.Pattern;

public class LinkNya {
	
	public static final Pattern REGEX_NYA_WEB_PATH = Pattern.compile("^(?:nya|meow|miao)[^/]*?/?$");
	
	@SuppressWarnings("unused") // unused feature https://nuko.sukazyo.cc/nya with same as tg://nya
	public static boolean isNyaPath (@NonNull String path) {
		return path.matches(REGEX_NYA_WEB_PATH.pattern());
	}
	
	public static boolean isNyaTgLink (@NonNull String url) {
		return url.startsWith("tg:meow") || url.startsWith("tg://meow") ||
				url.startsWith("tg:nya") || url.startsWith("tg://nya") ||
				url.startsWith("tg:miao") || url.startsWith("tg://miao");
	}
	
	public static void nya () {
		NotificationCenter.getGlobalInstance().postNotificationName(
				NotificationCenter.showBulletin,
				Bulletin.TYPE_ERROR,
				LocaleController.getString(R.string.NukoLinkNya)
		);
	}
	
}

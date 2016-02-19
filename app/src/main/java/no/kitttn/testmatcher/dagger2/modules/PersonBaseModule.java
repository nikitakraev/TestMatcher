package no.kitttn.testmatcher.dagger2.modules;

import android.content.Context;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmObject;
import no.kitttn.testmatcher.Matcher;
import no.kitttn.testmatcher.RxAPI;
import no.kitttn.testmatcher.UserGenerator;

/**
 * @author kitttn
 */
@Module
public class PersonBaseModule {
	private Context ctx;
	public PersonBaseModule(Context ctx) {
		this.ctx = ctx;
	}

	@Provides
	public Realm provideRealm() {
		Realm r = Realm.getInstance(ctx);
		r.setAutoRefresh(true);
		return r;
	}

	@Provides
	public Context provideContext() {
		return ctx;
	}

	@Provides
	public UserGenerator provideUserGenerator(EventBus bus, Realm realm, Context ctx) {
		return new UserGenerator(bus, realm, ctx);
	}

	@Provides
	public RxAPI provideAPI(Context ctx, Gson gson) {
		return new RxAPI(ctx, gson);
	}

	@Provides
	public Matcher provideMatcher(UserGenerator generator) {
		return new Matcher(generator);
	}

	@Provides
	public Gson provideGson() {
		return new GsonBuilder()
				.setExclusionStrategies(new ExclusionStrategy() {
					@Override
					public boolean shouldSkipField(FieldAttributes f) {
						return f.getDeclaringClass().equals(RealmObject.class);
					}

					@Override
					public boolean shouldSkipClass(Class<?> clazz) {
						return false;
					}
				})
				.create();
	}
}

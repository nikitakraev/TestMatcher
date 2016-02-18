package no.kitttn.testmatcher.presenters;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import no.kitttn.testmatcher.UserGenerator;
import no.kitttn.testmatcher.model.events.PersonListUpdatedEvent;
import no.kitttn.testmatcher.views.GeneratorView;

/**
 * @author kitttn
 */
public class GeneratorPresenter {
	private UserGenerator generator;
	GeneratorView view;

	public void setView(GeneratorView view) {
		this.view = view;
	}

	public void generateList() {
		view.loading();
		// TODO: replace with RxJava subscribe()
		generator.generate();
	}

	@Subscribe
	public void onPersonListUpdated(PersonListUpdatedEvent evt) {
		view.listLoaded();
	}

	public int getPersonsLeft() {
		return generator.getPersonsLeft();
	}

	@Inject
	public GeneratorPresenter(EventBus bus, UserGenerator generator) {
		this.generator = generator;
		bus.register(this);
	}
}

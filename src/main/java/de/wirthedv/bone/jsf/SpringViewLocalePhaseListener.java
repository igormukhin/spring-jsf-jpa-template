package de.wirthedv.bone.jsf;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * PhaseListener that sets locale of every JSF view to the locale from Spring context.
 * 
 * @author Igor Mukhin
 */
public class SpringViewLocalePhaseListener implements PhaseListener {

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // before new view is renderen is has to know it's locale
        if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
            setViewLocale(event.getFacesContext());
        }
        
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        // after view is restored it has to know the locale for validation and parsing
        if (PhaseId.RESTORE_VIEW.equals(event.getPhaseId())) {
            setViewLocale(event.getFacesContext());
        }
    }

    private void setViewLocale(FacesContext facesContext) {
        Locale locale = LocaleContextHolder.getLocale();
        facesContext.getViewRoot().setLocale(locale);
    }

}

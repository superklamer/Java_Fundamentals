package org.superklamer.javabrains.messenger.resources;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.superklamer.javabrains.messenger.service.MessageService;
import org.superklamer.javabrains.messenger.model.Message;
import org.superklamer.javabrains.messenger.resources.beans.MessageFilterBean;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML} )
public class MessageResource {
	
	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getStart()> 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		
		if (filterBean.getYear() > 0	)
			return messageService.getAllMessagesForYear(filterBean.getYear());
		return messageService.getAllMessages(); 
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
			.entity(newMessage)
			.build();
	}
//	public Message addMessage(Message message) {
//		return 	messageService.addMessage(message);
//	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		  messageService.removeMessage(id);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long messageId,
							@Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		message.addLink(getURIForSelf(uriInfo, message), "self");
		message.addLink(getURIForProfile(uriInfo, message), "profile");
		message.addLink(getURIForComments(uriInfo, message), "comments");
		return message;
	}

	private String getURIForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(MessageResource.class, "getCommentResource")
							.path(CommentResource.class)
							.resolveTemplate("messageId", message.getId())
							.build()
							.toString();
							
		return uri;
	}

	private String getURIForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
						.path(ProfileResource.class)
						.path(message.getAuthor())
						.build()
						.toString();
		
		return uri;
	}

	private String getURIForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();
		return uri;
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
